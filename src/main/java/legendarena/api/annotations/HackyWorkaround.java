package legendarena.api.annotations;

import legendarena.api.annotations.meta.BackendInfo;

import java.lang.annotation.Documented;

/**
 * Marks that the class or method is a hacky workaround to fix things, or make things more convenient.<br><br>
 *
 * @author ThePixelDev
 */
@Documented
@BackendInfo
public @interface HackyWorkaround {
}