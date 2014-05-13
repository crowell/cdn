/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.milton.http.webdav;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.xml.namespace.QName;

/**
 *
 * @author bradm
 */
public class PropertiesRequest {

	private final boolean allProp;
	private final Map<QName, Property> properties;

	public static PropertiesRequest toProperties(Set<QName> set) {
		Set<Property> props = new HashSet<Property>();
		for (QName n : set) {
			props.add(new Property(n, null));
		}
		return new PropertiesRequest(props);
	}

	public PropertiesRequest() {
		this.allProp = true;
		this.properties = new HashMap<QName, Property>();
	}

	public PropertiesRequest(Collection<Property> set) {
		this.allProp = false;
		this.properties = new HashMap<QName, Property>();
		for (Property p : set) {
			properties.put(p.getName(), p);
		}
	}

	public Property get(QName name) {
		return properties.get(name);
	}

	public void add(QName name) {
		properties.put(name, new Property(name, null));
	}

	public boolean isAllProp() {
		return allProp;
	}

	public Set<QName> getNames() {
		return properties.keySet();
	}

	public Collection<Property> getProperties() {
		return properties.values();
	}

	public static class Property {

		private final QName name;
		private final Map<QName, Property> nested;

		public Property(QName name, Set<Property> nestedSet) {
			this.name = name;
			this.nested = new HashMap<QName, Property>();
			if (nestedSet != null) {
				for (Property p : nestedSet) {
					nested.put(p.name, p);
				}
			}
		}

		public QName getName() {
			return name;
		}

		public Collection<Property> getNested() {
			return nested.values();
		}

		public Map<QName, Property> getNestedMap() {
			return nested;
		}
	}
}
