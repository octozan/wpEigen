/**
 * 
 */
package de.fom.wp.jpa;

import java.sql.*;

import org.eclipse.persistence.config.*;
import org.eclipse.persistence.descriptors.*;
import org.eclipse.persistence.internal.helper.*;
import org.eclipse.persistence.mappings.*;
import org.eclipse.persistence.sessions.*;
import org.eclipse.persistence.tools.schemaframework.*;

/**
 * @author ganeshs
 * 
 */
public class LowerCaseSessionCustomizer implements SessionCustomizer {

    @Override
    public void customize(Session session) throws SQLException {
        for (ClassDescriptor descriptor : session.getDescriptors().values()) {
            // Only change the table name for non-embedable entities with no
            // @Table already
            if (!descriptor.getTables().isEmpty() && descriptor.getAlias().equalsIgnoreCase(descriptor.getTableName())) {
                String tableName = addUnderscores(descriptor.getTableName());
                descriptor.setTableName(tableName);
                for (IndexDefinition index : descriptor.getTables().get(0).getIndexes()) {
                    index.setTargetTable(tableName);
                }
            }
            for (DatabaseMapping mapping : descriptor.getMappings()) {
                // Only change the column name for non-embedable entities with
                // no @Column already

                if (mapping instanceof AggregateObjectMapping) {
                    for (Association association : ((AggregateObjectMapping) mapping).getAggregateToSourceFieldAssociations()) {
                        DatabaseField field = (DatabaseField) association.getValue();
                        field.setName(addUnderscores(field.getName()));
                        
                        for (DatabaseMapping attrMapping : session.getDescriptor(((AggregateObjectMapping) mapping).getReferenceClass()).getMappings()) {
                            if (attrMapping.getAttributeName().equalsIgnoreCase((String) association.getKey())) {
                                ((AggregateObjectMapping) mapping).addFieldTranslation(field, addUnderscores(attrMapping.getAttributeName()));
                                ((AggregateObjectMapping) mapping).getAggregateToSourceFields().remove(association.getKey());
                                break;
                            }
                        }
                    }
                } else if (mapping instanceof ObjectReferenceMapping) {
                    for (DatabaseField foreignKey : ((ObjectReferenceMapping) mapping).getForeignKeyFields()) {
                        foreignKey.setName(addUnderscores(foreignKey.getName()));
                    }
                } else if (mapping instanceof DirectMapMapping) {
                    for (DatabaseField referenceKey : ((DirectMapMapping) mapping).getReferenceKeyFields()) {
                        referenceKey.setName(addUnderscores(referenceKey.getName()));
                    }
                    for (DatabaseField sourceKey : ((DirectMapMapping) mapping).getSourceKeyFields()) {
                        sourceKey.setName(addUnderscores(sourceKey.getName()));
                    }
                } else {
                    DatabaseField field = mapping.getField();
                    if (field != null && !mapping.getAttributeName().isEmpty() && field.getName().equalsIgnoreCase(mapping.getAttributeName())) {
                        field.setName(addUnderscores(mapping.getAttributeName()));
                    }
                }
            }
        }
    }

    private static String addUnderscores(String name) {
        if (name.equalsIgnoreCase("begintime")) {
            System.err.println();
        }
        StringBuffer buf = new StringBuffer(name.replace('.', '_'));
        for (int i = 1; i < buf.length() - 1; i++) {
            if (Character.isLowerCase(buf.charAt(i - 1)) && Character.isUpperCase(buf.charAt(i)) && Character.isLowerCase(buf.charAt(i + 1))) {
                buf.insert(i++, '_');
            }
        }
        return buf.toString().toLowerCase();
    }

}