/**
 * 
 */
package org.mybatis.generator.extend;

import java.math.BigDecimal;
import java.sql.Types;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

/**
 * @author Administrator
 *
 */
public class MyJavaTypeResolver  extends JavaTypeResolverDefaultImpl{

    
    @Override
    public FullyQualifiedJavaType calculateJavaType(
            IntrospectedColumn introspectedColumn) {
        FullyQualifiedJavaType answer;
        JdbcTypeInformation jdbcTypeInformation = typeMap
                .get(introspectedColumn.getJdbcType());

        if (jdbcTypeInformation == null) {
            switch (introspectedColumn.getJdbcType()) {
            case Types.DECIMAL:
            case Types.NUMERIC:
                if (introspectedColumn.getScale() > 0
                        || introspectedColumn.getLength() > 18
                        || forceBigDecimals) {
                    answer = new FullyQualifiedJavaType(BigDecimal.class
                            .getName());
                } 
               /* else if (introspectedColumn.getLength() > 9) {
                    answer = new FullyQualifiedJavaType(Long.class.getName());
                } 
                else if (introspectedColumn.getLength() > 4) {
                    answer = new FullyQualifiedJavaType(Integer.class.getName());
                } */
                else if (introspectedColumn.getLength() > 4) {
                    answer = new FullyQualifiedJavaType(Long.class.getName());
                } 
                else {
                    answer = new FullyQualifiedJavaType(Short.class.getName());
                }
                break;

            default:
                answer = null;
                break;
            }
        } else {
            answer = jdbcTypeInformation.getFullyQualifiedJavaType();
        }

        return answer;
    }
}
