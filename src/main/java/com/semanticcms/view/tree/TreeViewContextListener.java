/*
 * semanticcms-view-tree - SemanticCMS view of the tree of pages and elements starting at the current page.
 * Copyright (C) 2016  AO Industries, Inc.
 *     support@aoindustries.com
 *     7262 Bull Pen Cir
 *     Mobile, AL 36695
 *
 * This file is part of semanticcms-view-tree.
 *
 * semanticcms-view-tree is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * semanticcms-view-tree is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with semanticcms-view-tree.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.semanticcms.view.tree;

import com.semanticcms.core.servlet.SemanticCMS;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener("Registers the \"" + TreeView.VIEW_NAME + "\" view in SemanticCMS.")
public class TreeViewContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		SemanticCMS.getInstance(event.getServletContext()).addView(new TreeView());
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// Do nothing
	}
}
