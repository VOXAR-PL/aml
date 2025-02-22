/*
 * Copyright 2013-2015 (c) MuleSoft, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package org.raml.jaxrs.codegen.core.ext;

import org.aml.apimodel.Resource;

/**
 * <p>InterfaceNameBuilderExtension interface.</p>
 *
 * @author Kor
 * @version $Id: $Id
 */
public interface InterfaceNameBuilderExtension extends GeneratorExtension {
	
	/**
	 * Compose name of JAX RS interface
	 *
	 * @param resource RAML resource
	 * @return JAX RS interface name
	 */
	String buildResourceInterfaceName(final Resource resource);

}
