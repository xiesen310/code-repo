package top.xiesen.test.processor;

import top.xiesen.test.annoation.Simple;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * @Description SimpleProcessor
 * @Author 谢森
 * @Date 2019/8/17 15:54
 */
public class SimpleProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            System.out.println(annotation);
        }
        for (Element element : roundEnv.getElementsAnnotatedWith(Simple.class)) {
            display(element);
        }
        return false;
    }

    private void display(Element element) {
        System.out.println("==== " + element + " ====");

        System.out.println(element.getKind() +
                " : " + element.getModifiers() +
                " : " + element.getSimpleName() +
                " : " + element.asType());

        if (element.getKind().equals(ElementKind.CLASS)) {
            TypeElement te = (TypeElement) element;
            System.out.println(te.getQualifiedName());
            System.out.println(te.getSuperclass());
            System.out.println(te.getEnclosedElements());
        }

        if (element.getKind().equals(ElementKind.METHOD)) {
            ExecutableElement ex = (ExecutableElement) element;
            System.out.print(ex.getReturnType() + " ");
            System.out.print(ex.getSimpleName() + "(");
            System.out.println(ex.getParameters() + ")");
        }
    }
}
