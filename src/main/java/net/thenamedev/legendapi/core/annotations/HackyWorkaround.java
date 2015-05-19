package net.thenamedev.legendapi.core.annotations;

import net.thenamedev.legendapi.core.annotations.meta.BackendInfo;

import java.lang.annotation.Documented;

/**
 * Marks that the class or method is a hacky workaround to fix things, or make things more convenient.<br><br>
 *
 * @author ThePixelDev
 */
@Documented
@BackendInfo //this annotation is only to notify that a class/method is a hacky workaround, and shouldn't be referenced in actual code
public @interface HackyWorkaround {
}
