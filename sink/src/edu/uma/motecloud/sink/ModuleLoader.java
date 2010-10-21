package edu.uma.motecloud.sink;


public abstract class ModuleLoader {
	
	private static Module module;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Module main started");
		// Take args to commit the module using the Sink
		String location = args[0];
		String wsnID = args[1];
		String moduleClassName = args[2];
		Class moduleClass;
		try {
			moduleClass = Class.forName(moduleClassName);
			module = (Module) moduleClass.newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		// Create module using reflection
		module.initialize();
		module.setup(args);
		/* Start the Runnable task */		
		module.run();
	}


}
