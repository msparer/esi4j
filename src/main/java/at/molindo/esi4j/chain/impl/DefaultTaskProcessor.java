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
package at.molindo.esi4j.chain.impl;

import org.elasticsearch.action.ListenableActionFuture;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;

import at.molindo.esi4j.chain.Esi4JEntityTask;
import at.molindo.esi4j.chain.Esi4JTaskProcessor;
import at.molindo.esi4j.core.Esi4JIndex;
import at.molindo.esi4j.core.Esi4JIndex.IndexOperation;
import at.molindo.esi4j.core.Esi4JIndex.OperationHelper;

public class DefaultTaskProcessor extends AbstractTaskProcessor implements Esi4JTaskProcessor {

	public DefaultTaskProcessor(Esi4JIndex index) {
		super(index);
	}

	@Override
	public void processTasks(final Esi4JEntityTask[] tasks) {
		getIndex().executeBulk(new IndexOperation<ListenableActionFuture<BulkResponse>>() {

			@Override
			public ListenableActionFuture<BulkResponse> execute(Client client, String indexName, OperationHelper helper) {
				BulkRequestBuilder bulk = client.prepareBulk();

				for (int i = 0; i < tasks.length; i++) {
					tasks[i].addToBulk(bulk, indexName, helper);
				}

				return bulk.execute();
			}
		});
	}

}
