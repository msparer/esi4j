/**
 * Copyright 2010 Molindo GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.molindo.esi4j.module.hibernate.scrolling;

import java.util.Map;

import org.hibernate.FetchMode;


import com.google.common.collect.Maps;

public class DefaultQueryScrollingSessionProvider extends AbstractScrollingSessionProvider {

	private final Map<String, FetchMode> _fetchModes = Maps.newHashMap();

	public DefaultQueryScrollingSessionProvider(Class<?> type) {
		super(type);
	}

	public DefaultQueryScrollingSessionProvider setFetchMode(String associationPath, FetchMode fetchMode) {
		_fetchModes.put(associationPath, fetchMode);
		return this;
	}

	@Override
	public ScrollingSession newScrollingSession() {
		return new DefaultQueryScrollingSession(getType(), _fetchModes);
	}

}
