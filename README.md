# EcoreMetamodelExtraction
This project is a reverse engineering tool for Ecore metamodels. It allows extracting Ecore metamodels from any arbitrary Java code. At the core of the approach is a mapping from elements of the implicit Java metamodel, which the [Java Language Specification](https://docs.oracle.com/javase/specs/jls/se8/html/index.html) defines, to elements of the Ecore meta-metamodel. The project makes heavy use of the [EMF Ecore](http://download.eclipse.org/modeling/emf/emf/javadoc/2.9.0/org/eclipse/emf/ecore/package-summary.html) and the [Eclipse JDT](https://help.eclipse.org/oxygen/index.jsp?topic=%2Forg.eclipse.jdt.doc.isv%2Freference%2Fapi%2Foverview-summary.html) API.

Open Java projects in the Eclipse IDE can be selected to extract an Ecore Metamodel, which can be saved as an Ecore file. See the [wiki](https://github.com/tsaglam/EcoreMetamodelExtraction/wiki) for more details on the reverse engineering of Ecore metamodels.

## How to install:
1. Clone or download the project.
2. Import as existing project into the Eclipse IDE (preferably Eclipse 2018-09 or newer, tested up to 2020-06).
3. Make sure your IDE installation has log4j installed. If not you can get it from the latest [Orbit Build Repository](https://download.eclipse.org/tools/orbit/downloads/).
4. You need the [Eclipse Modeling Framework](https://www.eclipse.org/modeling/emf/) and the [Eclipse Java Development Tools](https://www.eclipse.org/jdt/), make sure that both are installed.
5. Run the project as Eclipse Application.
6. You can start the extraction from the context menu of a Java project or from the toolbar (provisional UI).
7. Extracting metamodels from projects with errors can cause problems while resolving types (or other problems). It is recommended to use code for the reverse engineering of Ecore metamodels that compiles without problems.
## Issues with current prototype
1. The project(s) must be opened in Eclipse as a workspace.
2. Google's JSON Simple is not used by the plugin for JSON analysis. Dynamic loading of JSON file is blocked but that's not necessary since the project data is already extracted.   
