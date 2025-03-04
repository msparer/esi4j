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
package at.molindo.esi4j.rebuild.util;

import java.util.Collections;
import java.util.List;

import at.molindo.esi4j.rebuild.Esi4JRebuildSession;

public final class EmptyRebuildSession implements Esi4JRebuildSession {

	private boolean _closed = false;
	private final Class<?> _type;

	public EmptyRebuildSession(Class<?> type) {
		if (type == null) {
			throw new NullPointerException("type");
		}
		_type = type;
	}

	@Override
	public boolean isOrdered() {
		return true;
	}

	@Override
	public Class<?> getType() {
		return _type;
	}

	@Override
	public List<?> getNext(int batchSize) {
		if (_closed) {
			throw new IllegalStateException("already closed");
		} else {
			return Collections.emptyList();
		}
	}

	@Override
	public Object getMetadata() {
		return null;
	}

	@Override
	public void close() {
		_closed = true;
	}

}
