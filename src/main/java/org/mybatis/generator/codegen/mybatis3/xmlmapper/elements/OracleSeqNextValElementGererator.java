package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * oracle seq 取值,seq命名规则　表名+"_seq"+
 * 
 * @author Administrator
 *
 */
public class OracleSeqNextValElementGererator extends AbstractXmlElementGenerator {

    @Override
    public void addElements(XmlElement parentElement) {

        XmlElement answer = new XmlElement("select"); //$NON-NLS-1$
        answer.addAttribute(new Attribute(
                "id", introspectedTable.getIdNextValue()
               
                )); //$NON-NLS-1$
        
        answer.addAttribute(new Attribute("resultType", //$NON-NLS-1$
                "java.util.Long"));
   

        context.getCommentGenerator().addComment(answer);

        StringBuilder sb = new StringBuilder();
       // sb.append("delete from "); //$NON-NLS-1$
        sb.append("select "); //$NON-NLS-1$  //将物理删除改为逻辑删除
        sb.append(introspectedTable.getFullyQualifiedTableNameAtRuntime()+"_seq.nextval from dual");
        answer.addElement(new TextElement(sb.toString()));

        parentElement.addElement(answer);
    }

}
