/*******************************************************************************
 *  Copyright (c) 2011 GitHub Inc.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  Contributors:
 *    Kevin Sawicki (GitHub Inc.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.github.internal;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.Assert;

/**
 * Repository service class.
 * 
 * @author Kevin Sawicki (kevin@github.com)
 */
public class RepositoryService {

	private GitHubClient client;

	/**
	 * Create repository service
	 * 
	 * @param client
	 *            cannot be null
	 */
	public RepositoryService(GitHubClient client) {
		Assert.isNotNull(client, "Client cannot be null"); //$NON-NLS-1$
		this.client = client;
	}

	/**
	 * Get all repositories accessible through organizational membership
	 * 
	 * @return list of repositories
	 * @throws IOException
	 */

	public List<Repository> getOrganizationRepositories() throws IOException {
		StringBuilder uri = new StringBuilder(IGitHubConstants.SEGMENT_V2_API);
		uri.append(IGitHubConstants.SEGMENT_ORGANIZATIONS).append(
				IGitHubConstants.SEGMENT_REPOSITORIES);

		RepositoryContainer container = client.get(uri.toString(),
				RepositoryContainer.class);
		return container.getResources();
	}

	/**
	 * Get repositories
	 * 
	 * @param user
	 * @return list of repositories
	 * @throws IOException
	 */
	public List<Repository> getRepositories(String user) throws IOException {
		StringBuilder uri = new StringBuilder(IGitHubConstants.SEGMENT_V2_API);
		uri.append(IGitHubConstants.SEGMENT_REPOS)
				.append(IGitHubConstants.SEGMENT_SHOW).append('/').append(user);

		RepositoryContainer container = client.get(uri.toString(),
				RepositoryContainer.class);
		return container.getResources();
	}
}
