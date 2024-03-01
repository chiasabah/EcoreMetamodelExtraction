package eme.handlers;
import eme.EcoreMetamodelExtraction;
import eme.properties.ExtractionProperties;
import eme.handlers.MainHandler;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;


/**
 * Handler for the Ecore metamodel extraction of specific project.
 * 
 * @author Timur Saglam
 */
public class ProjectHandlerBulk extends MainHandler {
    private static final Logger logger = LogManager.getLogger(EcoreMetamodelExtraction.class.getName());

	/**
	 * Base constructor.
	 */
	public ProjectHandlerBulk() {
		super();
	}

	/**
	 * Constructor that sets the title.
	 * 
	 * @param title is the title.
	 */
	public ProjectHandlerBulk(String title) {
		super(title);
	}

	/**
	 * Accesses the project form the selection and starts the extraction.
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		logger.info("Get list of projects");
		String projectNames[] = new String[]{
				"temporaryProject_2",
				"temporaryProject_3",
				"temporaryProject"
		};
		// Get the workspace
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
				
		logger.info("Start Extraction for the list of projects");
		
		for(String projectName: projectNames) {			
			logger.info("Extracting for " + projectName);			
			
			// Create the project
			IProject project = workspace.getRoot().getProject(projectName);

	        try {
	            // Import the project if it doesn't exist
	            if (!project.exists()) {
	            	logger.info("Project doesn't exist in workspace");
	                }
	        } catch (Exception e) {
	            e.printStackTrace();
	            logger.info("Error loading project: " + e.getMessage());
	        }
			
			if (isJavaProject(project)) {
				startExtraction((IProject) project);
			} else {
				projectMessage(event, " - detected folder.");
			}        
		}
		return null;
	}

	/**
	 * Warning message in case of a non-Java project.
	 */
	private void projectMessage(ExecutionEvent event, String additional) throws ExecutionException {
		String message = "This project is not a Java project. Therefore, a metamodel cannot be extracted." + additional;
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(window.getShell(), title, message);
	}

	/**
	 * Allows the configuration of the {@link ExtractionProperties} of the
	 * {@link EcoreMetamodelExtraction}.
	 * 
	 * @param properties are the {@link ExtractionProperties}.
	 */
	protected void configure(ExtractionProperties properties) {
		// Default: do nothing, use default properties.
	}

	/**
	 * Starts the extraction by calling an extraction method from the class
	 * {@link EcoreMetamodelExtraction}.
	 * 
	 * @param project is the parameter for the methods that is called.
	 */
	protected void startExtraction(IProject project) {
		EcoreMetamodelExtraction extraction = new EcoreMetamodelExtraction(); // EME instance
		configure(extraction.getProperties()); // configure extraction
		extraction.extract(project); // start
	}
}
