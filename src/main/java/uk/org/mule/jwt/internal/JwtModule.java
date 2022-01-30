package uk.org.mule.jwt.internal;

import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;
import org.mule.runtime.extension.api.annotation.error.ErrorTypes;
import static org.mule.runtime.api.meta.Category.COMMUNITY;

/**
 * This is the main class of the JWT Module plugin.
 */
@Configurations(JwtConfiguration.class)
@ErrorTypes(JwtError.class)
@Extension(name = "JWT", category = COMMUNITY)
@Xml(prefix = "jwt")
public class JwtModule {
}
