/**
 *  Smart Home Monitor Actions
 *
 *  Copyright 2016 Randy Shaddach
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
definition(
    name: "Smart Home Monitor Actions",
    namespace: "rjshadd",
    author: "Randy Shaddach",
    description: "Perform various actions when the Smart Home Monitor changes state",
    category: "Convenience",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")


preferences {
	page(name: "mainPage", title: "Smart Home Monitor Actions", uninstall: true, install: true) {
        section("SHM Set to Away") {
        	
            input "awayMode", "mode", title: "mode when away", multiple: false, required: false
            input "awaySwitchOn", "capability.switch", title: "turn these switches on", multiple: true, required: false
            input "awaySwitchOff", "capability.switch", title: "turn these switches off", multiple: true, required: false
            
        }
    }
}

def installed() {
	log.debug "Installed with settings: ${settings}"
	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"
	unsubscribe()
	initialize()
}

// subscribe to alarm state changes
def initialize() {
	subscribe(location, "alarmSystemStatus", alarmHandler)
}


// alarm state change event handler
def alarmHandler(evt) {
	log.debug "Alarm Handler value: ${evt.value}"
	//log.debug "alarm state: ${location.currentState("alarmSystemStatus")?.value}"
    
	if (evt.value == "off") {
    	log.debug "Alarm turned off"
    } else if (evt.value == "away") {
    	log.debug "Alarm set to away"
    } else if (evt.value == "stay") {
    	log.debug "Alarm set to stay"
    }
}