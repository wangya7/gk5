/*
 * Copyright 2012-2016 the original author or authors.
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

package wang.bannong.gk5.velocity.sb.web.servlet.view;


import wang.bannong.gk5.velocity.sf.web.VelocityView;
import wang.bannong.gk5.velocity.sf.web.VelocityViewResolver;

public class EmbeddedVelocityViewResolver extends VelocityViewResolver {

    private String toolboxConfigLocation;

    @Override
    protected void initApplicationContext() {
        if (this.toolboxConfigLocation != null) {
            if (VelocityView.class.equals(getViewClass())) {
                this.logger.info("Using EmbeddedVelocityToolboxView instead of "
                        + "default VelocityView due to specified toolboxConfigLocation");
                setViewClass(EmbeddedVelocityToolboxView.class);
            }
        }
        super.initApplicationContext();
    }

    @Override
    public void setToolboxConfigLocation(String toolboxConfigLocation) {
        super.setToolboxConfigLocation(toolboxConfigLocation);
        this.toolboxConfigLocation = toolboxConfigLocation;
    }

}