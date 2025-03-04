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
package at.molindo.esi4j.core.internal;

import org.elasticsearch.common.settings.Settings;

import at.molindo.esi4j.action.MultiGetItemResponseWrapper.MultiGetItemReader;
import at.molindo.esi4j.action.SearchHitWrapper.SearchHitReader;
import at.molindo.esi4j.core.Esi4JIndex;
import at.molindo.esi4j.core.Esi4JOperation.OperationContext;
import at.molindo.esi4j.core.Esi4JStore;
import at.molindo.esi4j.mapping.TypeMapping;

/**
 * private API, do not use unless you really know what you are doing
 */
public interface InternalIndex extends Esi4JIndex, OperationContext, SearchHitReader, MultiGetItemReader {

	InternalIndex addTypeMapping(TypeMapping typeMapping);

	void updateMapping(Class<?> type);

	void updateMapping(String typeAlias);

	/**
	 * @return the current {@link Esi4JStore} backing this index, never null
	 */
	Esi4JStore getStore();

	/**
	 * replaces index store and sets store's index
	 * 
	 * @param store
	 *            new store for this index
	 * 
	 * @throws NullPointerException
	 *             if store is null
	 * @throws IllegalStateException
	 *             if store was already assigned to an index
	 * @see Esi4JStore#setIndex(Esi4JIndex)
	 */
	void setStore(Esi4JStore store);

	/**
	 * @return the index's settings or null if default
	 */
	Settings getSettings();

}
