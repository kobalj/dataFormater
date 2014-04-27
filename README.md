dataFormater
============

Text formater for text with defined parameters writen in java. 

Usage
============
Implemented for two project of mine where we used a central systm to add addresses to the input data.

The implementation supports the following structure:
"text part"§"text part"§"text part"§

§ represent the delimiter that represents the boundary between single text parts of the the line.

"text part" contains the data and parameters for the formater.
Is is composed of:
text (optional) - ${paramName:fallBackValue(option)} (optional) - text (optional)

paramName is the name of the parameter to use for replacing with the value in the data object. 
fallBackValue is the fallback value used when there is no value for teh parameter in the data object.

Every part of the text is optional and not required. So teh line can have only text, only parameter with or without fallback value.

The start of the parameter is represented by ${ and ends with an }.
