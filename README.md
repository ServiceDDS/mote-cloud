# About to MoteCloud  

The integration of WSNs with back-end monitoring and management systems delivers the full value of them to enterprises 
and makes the connection of the physical world with the internet a reality, enabling the next-generation environmental 
science. The cloud computing concept is being used to define an Internet-based computing paradigm where shared resources, 
software, and information are provided to computers and other devices on demand. This can be applied to ambient 
monitoring with wireless sensor networks.  

[](https://github.com/jadianes/mote-cloud/blob/master/resources/body_pic.png?raw=true)
However, the degree of heterogeneity and scalability, together with the quality of service and event-based style of 
interaction typical of these scenarios, requires of specific supporting technologies if we want to provide an 
effective and competitive solution.  

MoteCloud is a DDS-based system for cloud monitoring of wireless sensor networks. DDS provides the high-performance 
and quality-enabled data and event distribution infrastructure. Extended with an XMPP-based protocol, it supports
an architecture that allows creating a global data space for dynamically integrate WSNs located at different 
Internet points, in a P2P style. Moreover, MoteCloud provides a set of user applications to perform the most usual 
and useful tasks required in ambient monitoring and that can be deployed in any location with Internet access.  

## Architecture drivers  

Several quality attributes have shaped the MoteCloud architecture:  

- Heterogeneity: MC have to deal with different WSN platforms. This is achieved by the Sink API that translates
WSN data to the DDS global data space. New modules can be developed using this API.  

- Performance: data must be delivered when generated, from WSN to applications. The applications do not need to check 
for new data all the time. Moreover, data quality of service (e.g. lost samples) have to be monitored. We achieve that 
using DDS quality of service parameters, its publish/subscribe model, and a "push" Web protocol (XMPP). Moreover, 
we avoid using centralized servers that can be overloaded, compromising performance. Our clouds have a P2P architecture.  

- Reliability: avoid single points of failure that could prevent applications to receive data. Our clouds have a P2P 
architecture. When a WSN fails, others keep serving its data to applications. There are not centralized servers that 
could stop the whole system from working.  

- Scalability: the architecture must be able to support locations with lots of data an requests. We achieve this using 
a DDS-distributed kernel in each location, that allows load-balancing thanks to its publish/susbcribe model and 
distributed global data space concept.   

- Dynamicity: the system must be able to change on run time either incorporating new WSN or new applications. This 
is achieved thanks to its microkernel architecture, where new modules can be plugged into the DDS backbone. 
Moreover, applications can appear at any moment since they use either the same DDS backbone or the XMPP Web protocol.  

- Complex event processing: an important part of monitoring applications is being able to define and detect complex 
events. This is achieved by introducing the concept of Complex Event Processor. A CEP is built on top of the DDS 
publish-subscribe model and incorporates user-defined routines.  

- Extensibility: MoteCloud must evolve, incorporating new WSN and new applications. It must promote a community based
development. This is done by using standards. New WSNs are incorporated developing simple Java modules that use 
the Sink API (it has three operations) to put data in the DDS global data space. Web applications only have to deal 
with the XMPP standard protocol. Therefore, extensibility points are well defined, and are mostly based on standard 
technologies.  


