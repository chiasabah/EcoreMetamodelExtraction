package eme.handlers;
import eme.EcoreMetamodelExtraction;
import eme.properties.ExtractionProperties;
import eme.handlers.MainHandler;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

import org.json.simple.JSONObject; //using json simple
import org.json.simple.parser.JSONParser;


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
		Map<String, List<String>> groupedKeys = extractGroupedKeys("project_details.json");
        // Print the returned HashMap if needed
        for (String groupKey : groupedKeys.keySet()) {
            List<String> fileKeys = groupedKeys.get(groupKey);
            for (String fileKey : fileKeys) {
                System.out.println("File Key: " + fileKey);
            }
        }
        
	    logger.info("Get list of projects");
	    //PREPEND TO THE FOLLOWING PROJECT PATHS, THE DIRECTORY.
	    //check output.txt for full list of 50k projects.
	    String projectPaths[] = new String[]{
	    		"0/bubba-h57-H57_Shiro",
	    		"0/moreira-javaee1",
	    		"0/freynaud-testng-support",
	    		"0/ariesteam-thinklab-api",
	    		"0/McLuke300-PlayerLogger",
	    		"0/pascaldimassimo-reservation-spring",
	    		"0/adammurdoch-native-platform",
	    		"0/stpolvi-TiRa-labra",
	    		"0/L8Fish-Circuits",
	    		"0/thebuzzmedia-imgscalr",
	    		"0/avshabanov-poker-calc",
	    		"0/fatality-Bank",
	    		"0/snopoke-Sid",
	    		"0/xsalefter-spring-hibernate",
	    		"0/vikrambindal-spring-rest-security",
	    		"0/uateam-app-team-uateam-app-java-core",
	    		"0/rmuller-infomas-asl",
	    		"0/nothingherenow-db-2012-project",
	    		"0/guardianproject-OlsrInfo",
	    		"0/Serubin-SeruBans",
	    		"0/benothman-mod_cluster-proxy",
	    		"0/dima767-inspektr",
	    		"0/Mitsugaru-KarmicLotto",
	    		"0/irobledo-DWGParser",
	    		"0/JavaPowder-TheJavaPowder",
	    		"0/ThomasGaubert-itemid",
	    		"0/sportscontroll-sportscontroll",
	    		"0/antonymarceles-28Game",
	    		"0/yorn42-facepalm",
	    		"0/Bukkit-DevBukkit",
	    		"0/LimeByte-HelpGUI",
	    		"0/tdunning-load",
	    		"0/kenyattaclark-otasco",
	    		"0/SOLA-FAO-messaging",
	    		"0/kedarmhaswade-interesting-problems",
	    		"0/Meyermagic-Tutoring2012",
	    		"0/herrbert74-CLCProcessor",
	    		"0/keel-s2j",
	    		"0/stackmob-stackmob-customcode-sdk",
	    		"0/talestk-jsf-blank",
	    		"0/ewolff-Spring-Integration-Web-Services-Batch-Demo",
	    		"0/mark-watson-java_practical_semantic_web",
	    		"0/willfaught-lab",
	    		"0/Factual-factual-java-driver",
	    		"0/kushal-diagram-server",
	    		"0/toby1984-3dtest",
	    		"0/patrickfreed-Sidecraft",
	    		"0/camnora-PasswordGenerator",
	    		"0/kinow-testopia-java-driver",
	    		"0/produnis-FreeQDA",
	    		"0/chetan-UASparser",
	    		"0/iainrose-page-objects",
	    		"0/krepko-GameChanger",
	    		"0/carefulcoder-JavaCity",
	    		"0/liuyong9061-MyTest",
	    		"0/topikachu-sparrow-runtime",
	    		"0/gemserk-artemis",
	    		"0/amadoru-starterkit-hibernate-springmvc-tiles",
	    		"0/fulltilt-CareerCup",
	    		"0/csadilek-errai-mvp-demo",
	    		"0/katrimonica-MasterMind",
	    		"0/posunisal-infoq-crawler",
	    		"0/dkhenry-Minecraft-JMX-Plugin",
	    		"0/srikanthps-yamx",
	    		"0/chandershivdasani-ProgrammingPearls",
	    		"0/safiri-gestaohotel",
	    		"0/rendermind-Nexus",
	    		"0/taustin-gamsa",
	    		"0/biasedbit-malabarista",
	    		"0/renmengye-AutoTetris",
	    		"0/Herocraft-Trade",
	    		"0/mihai-rotaru-MusicCollection",
	    		"0/jOOQ-jOOX",
	    		"0/holgero-XFD",
	    		"0/ttpekkan-symbolipeli",
	    		"0/rakama-Minecraft-WorldTools",
	    		"0/SuperSpyTX-SecretWord",
	    		"0/excilys-soja",
	    		"0/LogicLion-TheCoolGame",
	    		"0/mweagle-Logpig",
	    		"0/irenemachine-biblioteca",
	    		"0/wangdiao-PoiImport",
	    		"0/cgPrak12-Praktikum",
	    		"0/snarf95-Platform",
	    		"0/nanocom-console",
	    		"0/harshadura-LankaFuelMartCRM",
	    		"0/Bootscreen-Bukkit-CustomSlabs",
	    		"0/timmolter-Sundial",
	    		"0/mikaelhg-nondroid-mms",
	    		"0/hydrox-DroxPerms",
	    		"0/austin183-JavaProjectEuler",
	    		"0/flysnowxf-java_tools",
	    		"0/Motiejus-GLIProtoServer",
	    		"0/Emudofus-Mambo",
	    		"0/jadahl-jmdns",
	    		"0/inaiat-jqplot4java",
	    		"0/Shockah-EasySlick",
	    		"0/patrickkuo-minesweeper",
	    		"0/GretaCB-Routing-Project",
	    		"0/vencizon-Monopoly",
	    		"0/dpb06-team-boss-development",
	    		"0/Dirbaio-Minecraft-Protos",
	    		"0/cstenac-dbpedia2sql",
	    		"0/eric-brechemier-sitemapgen4j",
	    		"0/sap-production-XcodeProjectJavaAPI",
	    		"0/moffermann-harvest-client",
	    		"0/Mitsugaru-WorldChannels",
	    		"0/hugo53-ProductSimilarity",
	    		"0/irbis-SpringFramework-usage-examples",
	    		"0/ramhound-CommunityChest",
	    		"0/bahadurbaniya-Date-Converter-Bikram-Sambat-to-English-Date",
	    		"0/santoshjoshi-web-crawler",
	    		"0/okiandrew-ArcMapTools",
	    		"0/Thynix-routing-simulator",
	    		"0/isabel12-Cluedo-1",
	    		"0/jasondevj-regere-rule-engine",
	    		"0/delt0r-msms",
	    		"0/marcusbond-jax-rs-examples",
	    		"0/almo-AppEngine",
	    		"0/braegel-diagnozo_t5",
	    		"0/intesar-Spring-data-rest-sample",
	    		"0/sbastn-com.happyprog.pulse",
	    		"0/maurolopez-Trabajo-Practico-1",
	    		"0/gravypod-PersonalWorlds",
	    		"0/Soccer21x-SoftwareEngineering",
	    		"0/mctitan-DynamicSignMarket",
	    		"0/palerique-DesenvolvimentoOO",
	    		"0/ddopson-java-class-enumerator",
	    		"0/mehulsbhatt-VoIP-Voter",
	    		"0/timb-stud-prio-queue-shape-analysis",
	    		"0/Blazebit-blaze-cbav",
	    		"0/galaran-SpL-Editor",
	    		"0/Monstercraft-MonsterIRC",
	    		"0/ZeTRiX-McZLauncher",
	    		"0/PPilger-meketre-pelagios-mapping",
	    		"0/mjrcmrma-autoacceso",
	    		"0/devadastv-Lobber",
	    		"0/Technius-SkyQuest",
	    		"0/tehtros-TCastAPI",
	    		"0/kjw-supercheck",
	    		"0/maik656-Java-Test-Codes",
	    		"0/krot-s-vaadin",
	    		"0/cwensel-riffle",
	    		"0/markusbucher-she-sheazubi",
	    		"0/stleary-JSON-java",
	    		"0/astro-tools-diskEvolution",
	    		"0/ittobor-trh",
	    		"0/ctfy-nihuawocai_openfire_plugin",
	    		"0/mtomis-jurlmap",
	    		"0/nanoteam-nano_project",
	    		"0/jamessnee-Process_Characteriser",
	    		"0/tomcio930-refset",
	    		"0/Echocage-Scripts",
	    		"0/riotopsys-markov-chain",
	    		"0/khodzha-Impedance",
	    		"0/belaban-JGroups",
	    		"0/AlexGreulich-zombie-game",
	    		"0/Rayer-Utils",
	    		"0/topisark-Tiralabra",
	    		"0/dimitri-tiago-tinker",
	    		"0/obnoxint-ConsoleName",
	    		"0/typesafehub-config",
	    		"0/rupeshraut-eai",
	    		"0/metakevin-tds1logview",
	    		"0/thehutch-iSkin",
	    		"0/gsanthan-iPrefR",
	    		"0/creezo-RealWeather",
	    		"0/beanvalidation-beanvalidation-api",
	    		"0/choel-SpawnerAdjuster",
	    		"0/jonnysgomes-archetype",
	    		"0/nbuesing-opiblog-premock",
	    		"0/TesterViera-JavaLibTester",
	    		"0/marmur-mobicents-voip",
	    		"0/Vunovati-uskoci",
	    		"0/mebigfatguy-apophysis-j",
	    		"0/poidasmith-xlloop",
	    		"0/graphstream-gs-gephi",
	    		"0/banshee-AndroidProguardScala",
	    		"0/ucam-cl-dtg-ucam-webauth",
	    		"0/codebrothers-jpio",
	    		"0/raphaelpaiva-JTrace",
	    		"0/beckchr-juel",
	    		"0/matip-plistacontest",
	    		"0/drewhannay-chess",
	    		"0/viadee-viaRules",
	    		"0/thschuel-Therapeutic_Presence",
	    		"0/Konglic-jb_CRM",
	    		"0/corentin59-ArtNetStack",
	    		"0/kriegaex-Soccer-Table",
	    		"0/stackmob-stackmob-java-client-sdk",
	    		"0/sarala-ricordo-owlkb",
	    		"0/heignamerican-sandbox",
	    		"0/Silverpeas-OfficeOnline",
	    		"0/juhokallio-Telesina",
	    		"0/mrpdaemon-encfs-java",
	    		"0/juliano-simpledev",
	    		"0/duanrb-Game",
	    		"0/cip123-Tecla-Struts",
	    		"0/mstepan-my-repos",
	    		"0/josebezme-project-othello-2012",
	    		"0/mze9412-MzeHome",
	    		"0/jmgr2007-Reloader",
	    		"0/mikaelhg-jarjar",
	    		"0/juradoz-avayaJtapi",
	    		"0/phx-softcrafties-JavaHungarianAlgorithm",
	    		"0/nebulae2us-electron",
	    		"0/grnt426-RingMaster",
	    		"0/malla-TDA367",
	    		"0/roofimon-final-tdd-using-spring",
	    		"0/UnitecSPS-Programacion2_2_1_2012",
	    		"0/jsflive-mymail-get",
	    		"0/adi3-bricks",
	    		"0/Skymouse-Gaunt",
	    		"0/brockn-hadoop-training",
	    		"0/gravypod-ModifyWorld",
	    		"0/enobayram-OrxAnimationEditor",
	    		"0/ali85km-moosh-fyp",
	    		"0/thebuzzmedia-universal-binary-json-java",
	    		"0/Mitsugaru-AmazoCreative",
	    		"0/s4ke-BlockBreakerModel",
	    		"0/Gagravarr-VorbisJava",
	    		"0/vrecan-Util",
	    		"0/spullara-java-future-jdk8",
	    		"0/tonychiu25-TokyoMetro",
	    		"0/trbroyles1-ryans-java-playground",
	    		"0/dylanross-gitf",
	    		"0/argus-authz-argus-pep-common",
	    		"0/swetland-sonos",
	    		"0/dirkmc-press",
	    		"0/cdebortoli-requiem_on_m13",
	    		"0/balinjdl-OT-NT-Reference-Map-Processing",
	    		"0/retsrif-Slopes",
	    		"0/nirav99-MachineLearning",
	    		"0/Quigs314-Infested_Game",
	    		"0/Arnauld-sketchit",
	    		"0/agencia-autoacceso",
	    		"0/gurugv-wonderboard",
	    		"0/EqualExperts-json-schema-validator",
	    		"0/jhirniak-Javario",
	    		"0/VaibhavJain-JavaFx-2.0-Ludo",
	    		"0/rquilca-poo",
	    		"0/dredhorse-MessageChangerLite",
	    		"0/jorgefranconunes-tea",
	    		"0/Hypertopic-Porphyry",
	    		"0/hekonsek-camel-jira",
	    		"0/connaryscott-JaasDev",
	    		"0/julesjacobsen-CuratorModel",
	    		"0/nathanmarz-storm-kestrel",
	    		"0/EduBiz-beta",
	    		"0/trickl-trickl-sort",
	    		"0/roguePanda-minecraft-mod-client",
	    		"0/nysenate-Analytics",
	    		"0/rkonda-Tetris",
	    		"0/wolfc-jboss-libra",
	    		"0/hannawallach-java-lda",
	    		"0/snarf95-Pong",
	    		"0/sh2-jdbcrunner",
	    		"0/plausiblelabs-metrics-cloudwatch",
	    		"0/nadduck-MonteCarlo",
	    		"0/jboz-poc-jee6",
	    		"0/JoelJ-Templating",
	    		"0/jametsi-Huffman",
	    		"0/iainrose-vanq-java",
	    		"0/tig100-JdbcPgBackup",
	    		"0/skorg-perl-utils",
	    		"0/willwarren-easyzip4j",
	    		"0/victorcai0922-JmeterAnalysis",
	    		"0/Xefir-SimplyPerms",
	    		"0/heuer-sdshare-tests",
	    		"0/jafl-language_game",
	    		"0/colomoto-mddlib",
	    		"0/kawaa-Pigitos",
	    		"0/eamonnmag-SpreadsheetManipulator",
	    		"0/themadcreator-delaunay",
	    		"0/Jibbylala-SpringWS",
	    		"0/taobao-sqlautoreview",
	    		"0/Maescool-Catacomb-Snatch",
	    		"0/Exultant-Citadel",
	    		"0/Mitsugaru-AmazoCommand",
	    		"0/dkartaschew-acm-comp-guide",
	    		"0/jobbogamer-JSLibrary",
	    		"0/thermohaline-Hadoop-Training",
	    		"0/jed204-authy-java",
	    		"0/bartoszmajsak-arquillian-seam2-example",
	    		"0/luisrendon-empleado",
	    		"0/ebottabi-Picplx",
	    		"0/stormpath-stormpath-spring-samples",
	    		"0/exsolvi-maven-eclipse-configurator-plugin",
	    		"0/lolivera16-UPC_POO_EDU",
	    		"0/catchaser-Base-Commands",
	    		"0/Sovietaced-Avior",
	    		"0/omegalisk-procham"

	    };
	    // Get the workspace
	    IWorkspace workspace = ResourcesPlugin.getWorkspace();
	            
	    logger.info("Start Extraction for the list of projects");
	    
	    for(String projectPath: projectPaths) {         
	        logger.info("Extracting for " + projectPath);         
	        
	        // Create the project
	        IProject project = workspace.getRoot().getProject(getProjectNameFromPath(projectPath));

	        try {
	            // Import the project if it doesn't exist
	            if (!project.exists()) {
	                logger.info("Project doesn't exist in workspace");
	                IPath workspaceRootPath = ResourcesPlugin.getWorkspace().getRoot().getLocation();
	                logger.info("Current working directory: " + workspaceRootPath.toString());
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
	
	public static Map<String, List<String>> extractGroupedKeys(String fileName) {
        JSONParser parser = new JSONParser();
        Map<String, List<String>> groupedKeys = new HashMap<>();
        try {
            // Load JSON file
            Object obj = parser.parse(new FileReader(fileName));

            // Parse JSON object
            JSONObject jsonObject = (JSONObject) obj;

            // Iterate over keys
            Iterator<String> keys = jsonObject.keySet().iterator();
            while (keys.hasNext()) {
                String key = keys.next();
                String[] parts = key.split("/");
                String groupKey = parts[0]; // Extracting the initial number before "/"
                if (!groupedKeys.containsKey(groupKey)) {
                    groupedKeys.put(groupKey, new ArrayList<>());
                }
                groupedKeys.get(groupKey).add(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return groupedKeys;
    }

	private String getProjectNameFromPath(String projectPath) {
	    // Extracts the project name from the given path
	    String[] segments = projectPath.split("/");
	    return segments[segments.length - 1];
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
