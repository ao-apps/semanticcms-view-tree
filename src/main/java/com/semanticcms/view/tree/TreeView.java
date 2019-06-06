/*
 * semanticcms-view-tree - SemanticCMS view of the tree of pages and elements starting at the current page.
 * Copyright (C) 2016, 2017, 2018  AO Industries, Inc.
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

import com.aoindustries.encoding.TextInXhtmlEncoder;
import com.semanticcms.core.model.Page;
import com.semanticcms.core.model.PageRef;
import com.semanticcms.core.servlet.View;
import com.semanticcms.core.servlet.impl.NavigationTreeImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TreeView extends View {

	static final String VIEW_NAME = "tree";

	@Override
	public Group getGroup() {
		return Group.FIXED;
	}

	@Override
	public String getDisplay() {
		return "Tree";
	}

	@Override
	public String getName() {
		return VIEW_NAME;
	}

	@Override
	public String getTitle(
		ServletContext servletContext,
		HttpServletRequest request,
		HttpServletResponse response,
		Page page
	) {
		String bookTitle = page.getPageRef().getBook().getTitle();
		if(bookTitle != null && !bookTitle.isEmpty()) {
			return "Page Tree" + TITLE_SEPARATOR + page.getTitle() + TITLE_SEPARATOR + bookTitle;
		} else {
			return "Page Tree" + TITLE_SEPARATOR + page.getTitle();
		}
	}

	@Override
	public String getDescription(Page page) {
		return null;
	}

	@Override
	public String getKeywords(Page page) {
		return null;
	}

	/**
	 * This view does not provide additional information unobtainable from source content,
	 * exclude from search indexes.
	 */
	@Override
	public boolean getAllowRobots(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response, Page page) {
		return false;
	}

	@Override
	public void doView(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException {
		PageRef pageRef = page.getPageRef();
		PrintWriter out = response.getWriter();
		out.print("<h1>Page Tree of ");
		TextInXhtmlEncoder.encodeTextInXhtml(page.getTitle(), out);
		out.println("</h1>");
		NavigationTreeImpl.writeNavigationTreeImpl(
			servletContext,
			request,
			response,
			out,
			page,
			false, // skipRoot
			false, // yuiConfig
			true, // includeElements
			null, // target
			pageRef.getBookName(),
			pageRef.getPath(),
			null, // linksToBook
			null, // linksToPage
			0
		);
	}
}
