Exercise 1 is here: https://github.com/mnavarromxgit/challenge/blob/lambdatest/src/main/java/com/exercises/Anagram.java <BR>
Exercise 2 is here: https://github.com/mnavarromxgit/challenge/blob/lambdatest/src/main/java/com/exercises/BalancedParenthesis.java <BR><BR>
Exercise 3 handling logic is here: https://github.com/mnavarromxgit/challenge/blob/lambdatest/src/main/java/com/lambda/LambdaApiGateway.java <BR>
Below steps show how to use implemented function with API Gateway. When String doesn't match requirements, response include message explaining why, as suggested on the requirement.

<h1> Exercise 3 Lambda Function Set Up</h1>

Prerequirements:
<li> AWS account available and AWS CLI locally configured</li>
<li> S3 Bucket is created and accessible to allocate objects on it using CLI</li>
<li> Maven 3</li>
<li> Java 17</li>
<BR><BR>
To Set up lambda function, follow one of the 2 methods below

<h3> Method 1 </h3>

1 - On root folder run the following command: <BR>
mvn clean package

2 - Run the following aws commands on project root folder. Set the bucket name on s3-bucket part: <BR>
aws cloudformation package --template-file template.yml --s3-bucket [BUCKETNAME] --output-template-file out.yml <BR>
aws cloudformation deploy --template-file out.yml --stack-name hashingfunctionstack --capabilities CAPABILITY_NAMED_IAM <BR><BR>
Go to the AWS Management Console and navigate Lambda service. Go to functions and open the new generated hashingFunction

3 - Go to Configuration Tab and go to Triggers. Click Add Trigger button

4 - Select API Gateway as a source

5 - On Intent field, select Create New Api. Set Security as Open and Api Type as HTTP Api

6 - On the generated API, use the API endpoint to make calls to the lambda function.

7 - The verb accepted is POST to get the Hashed values. The content type should be text/plain.
<BR>
*****Note that adding double quotes if testing from postman, application will consider them as special characters.
<BR><BR>
Example:<BR>
curl -X POST -H 'content-type: text/plain' --data "stringtohash" apiendpoint.aws.com


<h3> Method 2  (Just in case of unavailability of AWS Cli or S3 bucket creation )</h3>

1 - On root folder run the following command:
   mvn clean package

2 - On AWS Management Console, navigate to AWS Lambda and click on "Create Function" button

3 - Use the option "Author from scratch"

4 - On Basic Information section, type any descriptive name for the function and for Runtime field, select Java 17

5 - Leave the rest of the fields with default options and click on "Create Function" button

6 - Once the function is created go to Coding tab and on Runtime settings change the handler to com.lambda.LambdaApiGateway::handleRequest

7 - On Code Source, click the Upload From button, and upload the file target/lambda-function-0.1.0-SNAPSHOT generated on step #1

8 - Follow steps 3 - 7 of previous section

