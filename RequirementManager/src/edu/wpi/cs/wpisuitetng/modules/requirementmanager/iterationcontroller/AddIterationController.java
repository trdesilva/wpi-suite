/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.iterationcontroller;


import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;

/**
 * This controller responds when the user clicks the Update button by
 * adding the contents of the Iteration text fields to the model as a new
 * Iteration.
 */
public class AddIterationController{
	
	private static AddIterationController instance;
	private AddIterationRequestObserver observer;
	
	/**
	 * Construct an AddIterationController for the given model, view pair
	 * @param model the model containing the Iterations
	 * @param view the view where the user enters new Iterations
	 */
	private AddIterationController() {
		observer = new AddIterationRequestObserver(this);
	}
	
	/**
	 * Returns the instance of the AddIterationController or creates one if it does not
	 * exist.
	 */
	public static AddIterationController getInstance()
	{
		if(instance == null)
		{
			instance = new AddIterationController();
		}
		
		return instance;
	}

	/**
	 * This method adds a Iteration to the server.
	 * @param newIteration is the Iteration to be added to the server.
	 */
	public void addIteration(Iteration newIteration) 
	{
		final Request request = Network.getInstance().makeRequest("requirementmanager/iteration", HttpMethod.PUT); // PUT == create
		request.setBody(newIteration.toJSON()); // put the new Iteration in the body of the request		
		request.addObserver(observer); // add an observer to process the response
		request.send(); 
	}
}
