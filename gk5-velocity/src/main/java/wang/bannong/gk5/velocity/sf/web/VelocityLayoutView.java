/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wang.bannong.gk5.velocity.sf.web;

import java.io.StringWriter;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.exception.ResourceNotFoundException;

import org.springframework.core.NestedIOException;

public class VelocityLayoutView extends VelocityToolboxView {

    public static final String DEFAULT_LAYOUT_URL = "layout.vm";

    public static final String DEFAULT_LAYOUT_KEY = "layout";

    public static final String DEFAULT_SCREEN_CONTENT_KEY = "screen_content";


    private String layoutUrl = DEFAULT_LAYOUT_URL;

    private String layoutKey = DEFAULT_LAYOUT_KEY;

    private String screenContentKey = DEFAULT_SCREEN_CONTENT_KEY;

    public void setLayoutUrl(String layoutUrl) {
        this.layoutUrl = layoutUrl;
    }

    public void setLayoutKey(String layoutKey) {
        this.layoutKey = layoutKey;
    }

    public void setScreenContentKey(String screenContentKey) {
        this.screenContentKey = screenContentKey;
    }


    @Override
    public boolean checkResource(Locale locale) throws Exception {
        if (!super.checkResource(locale)) {
            return false;
        }

        try {
            // Check that we can get the template, even if we might subsequently get it again.
            getTemplate(this.layoutUrl);
            return true;
        } catch (ResourceNotFoundException ex) {
            throw new NestedIOException("Cannot find Velocity template for URL [" + this.layoutUrl +
                    "]: Did you specify the correct resource loader path?", ex);
        } catch (Exception ex) {
            throw new NestedIOException(
                    "Could not load Velocity template for URL [" + this.layoutUrl + "]", ex);
        }
    }


    @Override
    protected void doRender(Context context, HttpServletResponse response) throws Exception {
        renderScreenContent(context);

        // Velocity context now includes any mappings that were defined
        // (via #set) in screen content template.
        // The screen template can overrule the layout by doing
        // #set( $layout = "MyLayout.vm" )
        String layoutUrlToUse = (String) context.get(this.layoutKey);
        if (layoutUrlToUse != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("Screen content template has requested layout [" + layoutUrlToUse + "]");
            }
        } else {
            // No explicit layout URL given -> use default layout of this view.
            layoutUrlToUse = this.layoutUrl;
        }

        mergeTemplate(getTemplate(layoutUrlToUse), context, response);
    }

    private void renderScreenContent(Context velocityContext) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("Rendering screen content template [" + getUrl() + "]");
        }

        StringWriter sw = new StringWriter();
        Template screenContentTemplate = getTemplate(getUrl());
        screenContentTemplate.merge(velocityContext, sw);

        // Put rendered content into Velocity context.
        velocityContext.put(this.screenContentKey, sw.toString());
    }

}
