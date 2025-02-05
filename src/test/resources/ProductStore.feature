Feature: Product - Store

  Scenario: Validación del precio de un producto
    Dado estoy en la página de la tienda
    Y me logueo con mi usuario "userValido" y clave "passValido"
    Cuando navego a la categoria "Clothes" y subcategoria "Men"
    Y agrego 2 unidades del primer producto al carrito
    Entonces valido en el popup la confirmación del producto agregado
    Y valido en el popup que el monto total sea calculado correctamente
    Cuando finalizo la compra
    Entonces valido el titulo de la pagina del carrito
    Y vuelvo a validar el calculo de precios en el carrito

  Scenario: Usuario y contraseña inválidos
    Dado estoy en la página de la tienda
    Y me logueo con mi usuario "usuarioInvalido" y clave "claveInvalida"
    Entonces no debo acceder a la pantalla principal

  Scenario: Categoría inexistente
    Dado estoy en la página de la tienda
    Y me logueo con mi usuario "userValido" y clave "passValido"
    Cuando navego a la categoria "Autos" y subcategoria "Men"
    Entonces la automatización se detiene y se registra el error de categoría inexistente