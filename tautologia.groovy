//Take Input
def inputPane = javax.swing.JOptionPane.&showInputDialog
def input = inputPane 'Enter your text :'
assert input.length() > 0 : "Empty input"
assert input.length() < 1000000 : "Input too long"
//Get Ignore Length
def ignoreLength = inputPane 'Enter ignore length :'
assert ignoreLength.isInteger() : "Non-integer number entered!"
ignoreLength = Integer.parseInt(ignoreLength)
//Get Repetition Threshold
def repetitionThreshold = inputPane 'Enter repetition threshold :'
assert repetitionThreshold.isInteger() : "Non-integer number entered!"
repetitionThreshold = Integer.parseInt(repetitionThreshold)

//Define Output Pane
def outputString = ""

//Repeat Input
println "Got input : $input"

//Split into List
def content = [];
content = input.split(" ") as List

//Standardize List Data
    //remove blank chars and spaces
content.removeAll{ it.trim().length() <= 0 }
    //replace spaces
content = content*.replaceAll(" ","")
    //make lower case
content = content*.toLowerCase()
    //replace non alpha numeric
content = content*.replaceAll("[^a-z0-9]","")

//Setup Counter
def count = [:]
content.each{
    if(count.containsKey(it)){
        count[it] += 1
    }
    else{
        count.put(it, 1)
    }
}

//Ensure Correct Counting
count.values.each{
    assert it > 0 : "Word Count Error"
}

//Iterate Map and Flag Words
def thiscount = -1
count.keySet().each{
    if(it.length() > ignoreLength){
        if(count[it] >= repetitionThreshold){
            thiscount = count[it]
            assert thiscount > 0 : "Counting error"
            outputString += "$it has count $thiscount\n"
        }
    }
}

//Output
javax.swing.JOptionPane.showMessageDialog(null, outputString)