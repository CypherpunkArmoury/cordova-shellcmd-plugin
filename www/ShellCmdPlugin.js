window.run = function(cmd, successCallback, errorCallback) {
	cordova.exec(successCallback, errorCallback, "ShellCmdPlugin", "run", [cmd]);
};
