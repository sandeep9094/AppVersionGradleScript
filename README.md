# AppVersionGradleScript
Gradle Script for Automate app version.

<h5>Gradle script is implmented in demo project which is given below.<h5>

Steps for Creating Gradle Build Script
----

1.  Create new Resource Bundle file *(name : version)*

    <i><h6> app > New  > Resource Bundle</h6> </i>

2.  Open <i>version.properties</i> in app module and write below lines in file. 
    ```
    VERSION_NUMBER=1
    VERSION_MINOR=0
    VERSION_BUILD=0
    VERSION_PATCH=0
    VERSION_MAJOR=0
    ```
3.  Open build.gradle (Module: app) and gradle script lines as shown below.

```groovy
/**
    * Auto Increment Build Version Script
    */
def versionPropsFile = file('version.properties')

/*Setting default value for versionBuild which is the last incremented value stored in the file */
if (versionPropsFile.canRead()) {
        Properties versionProps = new Properties()
        versionProps.load(new FileInputStream(versionPropsFile))
        def value = 0
        def runTasks = gradle.startParameter.taskNames
        if ('assemble' in runTasks || 'assemebleRelease' in runTasks || 'aR' in runTasks) {
            value = 1
        }
    
        //Getting Strings from version.properties
        def versionMajor = versionProps['VERSION_MAJOR'].toInteger()
        def versionMinor = versionProps['VERSION_MINOR'].toInteger()
        def versionPatch = versionProps['VERSION_PATCH'].toInteger() + value
        def versionBuild = versionProps['VERSION_BUILD'].toInteger() + 1
        def versionNumber = versionProps['VERSION_NUMBER'].toInteger() + value

        //Saving modified values in version.properties
        versionProps['VERSION_PATCH'] = versionPatch.toString()
        versionProps['VERSION_BUILD'] = versionBuild.toString()
        versionProps['VERSION_NUMBER'] = versionNumber.toString()

        versionProps.store(versionPropsFile.newWriter(), null)

        defaultConfig {
            applicationId ""
            minSdkVersion 16
            targetSdkVersion 29
            versionCode versionNumber
            versionName "${versionMajor}.${versionMinor}.${versionPatch}.${versionBuild}"
            testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
            multiDexEnabled true
        }
    } else {
        throw new FileNotFoundException("Could not find version.properties")
    }
```
