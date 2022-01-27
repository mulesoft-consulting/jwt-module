package uk.org.mule.jwt.internal;

import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;
import static org.mule.runtime.api.meta.Category.COMMUNITY;


/**
 * This is the main class of an extension, is the entry point from which configurations, connection providers, operations
 * and sources are going to be declared.
 */
@Xml(prefix = "jwt")
@Extension(name = "JWT", category = COMMUNITY)
@Configurations(JwtConfiguration.class)
public class JwtModule {
}
