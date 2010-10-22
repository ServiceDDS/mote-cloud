package edu.uma.motecloud.sink;

import edu.uma.motecloud.sink.exceptions.ImpossibleToRegisterModule;
import ServiceDDS.Group;
import ServiceDDS.Peer;
import ServiceDDS.exception.ImpossibleToCreateDDSDomainParticipant;

/**
 * A Sink is used to commit modules to the global data space
 * @author Jose Angel Dianes
 * @version 0.1a
 * @date 10/15/2010
 */
public class Sink {
	
	/**
	 * Instantiates the ServiceDDS Peer class and joins to group
	 * @param m
	 * @return
	 */
	public static Peer registerModule(String wsnID, String location) throws ImpossibleToRegisterModule {
		Peer res;
		try {
			res = new Peer(wsnID+"@"+location);
			res.joinGroup(new Group("MoteCloud"));
			System.out.println("Sing: module registered at "+wsnID+"@"+location);
		} catch (ImpossibleToCreateDDSDomainParticipant e) {
			throw new ImpossibleToRegisterModule();
		} 
		return res;
	}
	
}
