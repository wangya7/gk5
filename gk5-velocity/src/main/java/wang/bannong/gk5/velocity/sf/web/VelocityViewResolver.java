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

import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

public class VelocityViewResolver extends AbstractTemplateViewResolver {

	private String dateToolAttribute;

	private String numberToolAttribute;

	private String toolboxConfigLocation;


	public VelocityViewResolver() {
		setViewClass(requiredViewClass());
	}

	@Override
	protected Class<?> requiredViewClass() {
		return VelocityView.class;
	}


	public void setDateToolAttribute(String dateToolAttribute) {
		this.dateToolAttribute = dateToolAttribute;
	}

	public void setNumberToolAttribute(String numberToolAttribute) {
		this.numberToolAttribute = numberToolAttribute;
	}

	public void setToolboxConfigLocation(String toolboxConfigLocation) {
		this.toolboxConfigLocation = toolboxConfigLocation;
	}


	@Override
	protected void initApplicationContext() {
		super.initApplicationContext();

		if (this.toolboxConfigLocation != null) {
			if (VelocityView.class == getViewClass()) {
				logger.info("Using VelocityToolboxView instead of default VelocityView " +
						"due to specified toolboxConfigLocation");
				setViewClass(VelocityToolboxView.class);
			}
			else if (!VelocityToolboxView.class.isAssignableFrom(getViewClass())) {
				throw new IllegalArgumentException(
						"Given view class [" + getViewClass().getName() +
						"] is not of type [" + VelocityToolboxView.class.getName() +
						"], which it needs to be in case of a specified toolboxConfigLocation");
			}
		}
	}


	@Override
	protected AbstractUrlBasedView buildView(String viewName) throws Exception {
		VelocityView view = (VelocityView) super.buildView(viewName);
		view.setDateToolAttribute(this.dateToolAttribute);
		view.setNumberToolAttribute(this.numberToolAttribute);
		if (this.toolboxConfigLocation != null) {
			((VelocityToolboxView) view).setToolboxConfigLocation(this.toolboxConfigLocation);
		}
		return view;
	}

}
