
# Property Rental Management System (PRMS)

## Tasks

* A complete list of systems actors: people, other systems, devices (if applies)
* For each actor:  A  brief description. couple of sentences
* For each actor: A list of major events that triggers a process on the system. 
* You high level use case diagram, using StarUML. Please be aware of the fact that use case is not a flowchart. Its only purpose is to illustrate your term project's scope (what is within and what is outside your target system). Please indication of major high level User/Devices/Other systems interfaces. Examples include, required high-level GUIs, interfaces with the other systems such as database connectivity requirements, etc.

## Actors

### Landlord

* The **owner** of the property being rented that **registers** the property to the online management system and **pays** a fee to have the property ad posted which can be viewed by renters. The fee is charged for the amount of the time the owner wants the ad to remain on the system.
* List of major events:

	* registration of property to system
	* payment of fees

### Manager

### Renters
* Regular Renters

	* These renters are like **observers** of the system that **don't need to login** and are allowed to **search** the system for listings. Regular renters can also send email to the landord without seeing their info.
	* List of Major Events:

		* Search query for listings
		* Send email to landlord

* Registered Renters

	* These rents **must login** to the system and can **search** for listings with the same critera listed below. However, registered renters will be **notified** when new listings are posted that match their critera. Registered renters can also send email to the landord without seeing their info.
	* List of Major Events:

		* Login to the system
		* Search Query for listings
		* Storing of search queries to database

			* Required to comapre against new listings

		* Notification of new listing
		* Send email to landlord

	* Search Criteria for Renters

		* Apartment, attached/detached house, townhouse, etc.
		* Number of bedrooms,
		* Number of bathrooms
		* Furnished/unfurnished
		* City quadrant: SW, NW, NE, SE

### Database Engine




