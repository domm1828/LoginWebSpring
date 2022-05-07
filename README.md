# Web Login Spring Boot
## _Spring MVC + Spring Security + MySQL_

[![N|Solid](https://pbs.twimg.com/profile_images/1314007313276166144/iu-k7z-C_400x400.jpg)](https://duglasm.wordpress.com/)

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://github.com/domm1828/LoginWebSpring)

Instalar las dependencias necesarias para el proyecto

- Crear tu configurar de *SecurityConfiguration* es una implementacion de *WebMvcConfigurer* para agregar la opcion de *passwordEncoder*
- Crear tu configuracion de *WebSecurityConfiguration* extendiendo de *WebSecurityConfigurerAdapter* para verificar la autenticacion y crear las reglas de las rutas visibles y las rutas de contro de acceso.
- En la misma configiracion agregar los patch del ignore de la autenticacion

## Features

- Crear los modelos de roles y usuarios
- Crear los repositorio de los roles y usuarios
- Crear dos servicios 1.- para el registro de usuario y 2.- Para la verificacion de los datos del usuarios
- Crear el Controllador
- Crea tus vistas
 
