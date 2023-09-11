# Macmil UI Tests #
This project automates UI tests of the responsive website in desktop and mobile mode with each scenario starting on the home page.
It provides a scenario of checking the prices of particular items on the market page.

This is run twice. Each time against a different item.

The project is based on Maven, Selenium, Cucumber and TestNG and is fully Docker'ised.

## Prerequisites ##
In order to execute the set of tests provided by this framework following prerequisites are necessary:

- Docker
- Docker Compose
- Git

## Execution ##
Please follow the below steps to execute the test suite:

- Clone the project repository from here - ```git clone https://github.com/maciejmilgiewicz/macmil```
- Open docker-compose.yml, which is in the project's root directory
- Replace ```<HOMEPAGE_URL>``` placeholder in ```-Dtest.url='<HOMEPAGE_URL>'``` property within the .yml file to the home page URL of your website
- Choose the mode in which you want the tests to be executed, e.g. ```-Dbrowser.viewport='DESKTOP'``` or ```-Dbrowser.viewport='MOBILE'```. This will automatically resize the browser, as needed
- If port ```5900``` on the host running the framework is already allocated, please adjust VNC host port for Selenium container, i.e. setup ```<VNC_HOST_PORT>``` accordingly
```text
selenium:
  ports:
    - "<VNC_HOST_PORT>:5900"
```
- To start the tests please execute ```docker-compose up```
- Containers will auto-discover each other via configured Docker Compose dependencies 
- Selenium container browser can be observed for debugging purposes via VNC viewer on ```vnc://<HOST_IP>:<VNC_HOST_PORT>```, e.g. ```vnc://localhost:5900```. The VCN password is **secret**
- To tear the containers after tests please run ```docker-compose stop``` followed by ```docker-compose rm```
- Cucumber report can be found in ```./target/cucumber-reports/index.html```

If an error about missing engine dependency appears, please run `mvn clean install` against the engine module.

## Author ##
Maciej Milgiewicz

 