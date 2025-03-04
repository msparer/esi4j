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
package at.molindo.esi4j.core;

import org.elasticsearch.client.Client;

import at.molindo.esi4j.mapping.TypeMapping;

public interface Esi4JOperation<T> {

	T execute(Client client, String indexName, OperationContext helper);

	public interface OperationContext {

		/**
		 * @return never null
		 * 
		 * @throws IllegalArgumentException
		 *             is no sub-index or multiple sub-indices of this context
		 *             have a mapping for this type
		 * 
		 */
		String findIndexName(Class<?> type);

		/**
		 * @throws IllegalArgumentException
		 *             if not mapped
		 */
		TypeMapping findTypeMapping(Object o);

		/**
		 * @throws IllegalArgumentException
		 *             if not mapped
		 */
		TypeMapping findTypeMapping(Class<?> type);

		/**
		 * @param indexName
		 *            ES index name
		 * @param typeAlias
		 *            ES type
		 * @throws IllegalArgumentException
		 *             if not mapped
		 */
		TypeMapping findTypeMapping(String indexName, String typeAlias);
	}
}