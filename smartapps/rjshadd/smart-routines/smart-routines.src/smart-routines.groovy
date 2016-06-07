/**
 *  Smart Routines
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
	name: "Smart Routines",
	namespace: "rjshadd/smart routines",
	author: "Randy Shaddach",
	description: "Parent of the Smart Routines SmartApp. Executes a routine based on a simple trigger.",
	category: "Convenience",
	iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
	iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
	iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
	singleInstance: true // prevent users from installing more than one parent smartapp
	)


preferences {
	page(name: "mainPage", title: "Child Apps", install: true, uninstall: true) {
		section {
			app(name: "childApps", appName: "Routine Automation", namespace: "rjshadd/smart routines", title: "New Routine Automation", multiple: true)
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

def initialize() {
	// nothing needed here, since the child apps will handle preferences/subscriptions
	log.debug "there are ${childApps.size()} child smartapps"
	childApps.each {child ->
        log.debug "child app: ${child.label}"
    }
}
