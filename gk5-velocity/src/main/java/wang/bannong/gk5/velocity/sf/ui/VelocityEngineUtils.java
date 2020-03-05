/*
 * Copyright 2002-2013 the original author or authors.
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

package wang.bannong.gk5.velocity.sf.ui;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;

public abstract class VelocityEngineUtils {

	@Deprecated
	public static void mergeTemplate(
			VelocityEngine velocityEngine, String templateLocation, Map<String, Object> model, Writer writer)
			throws VelocityException {

		VelocityContext velocityContext = new VelocityContext(model);
		velocityEngine.mergeTemplate(templateLocation, velocityContext, writer);
	}

	public static void mergeTemplate(
			VelocityEngine velocityEngine, String templateLocation, String encoding,
			Map<String, Object> model, Writer writer) throws VelocityException {

		VelocityContext velocityContext = new VelocityContext(model);
		velocityEngine.mergeTemplate(templateLocation, encoding, velocityContext, writer);
	}

	@Deprecated
	public static String mergeTemplateIntoString(VelocityEngine velocityEngine, String templateLocation,
			Map<String, Object> model) throws VelocityException {

		StringWriter result = new StringWriter();
		mergeTemplate(velocityEngine, templateLocation, model, result);
		return result.toString();
	}

	public static String mergeTemplateIntoString(VelocityEngine velocityEngine, String templateLocation,
			String encoding, Map<String, Object> model) throws VelocityException {

		StringWriter result = new StringWriter();
		mergeTemplate(velocityEngine, templateLocation, encoding, model, result);
		return result.toString();
	}

}
