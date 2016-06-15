package br.projecao.procura.beans.generic;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.enterprise.context.NormalScope;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target(value={METHOD,TYPE,FIELD})
@Retention(value=RUNTIME)
@NormalScope
@Inherited
public @interface ViewScoped {

}