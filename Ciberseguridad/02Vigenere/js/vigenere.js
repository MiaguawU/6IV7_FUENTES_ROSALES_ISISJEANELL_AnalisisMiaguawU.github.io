var vigenere = vigenere || (function () {
    var proceso = function (txt, clave, action) {
        var abc = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'];
        var longitud = abc.length;

        var resultado = "";
        var indiceClave = 0;

        for (var i = 0; i < txt.length; i++) {
            var c = txt.charAt(i).toLowerCase();
            var iTxt = abc.indexOf(c);

            if (iTxt !== -1) {
                var desp = abc.indexOf(clave.charAt(indiceClave).toLowerCase());

                if (action) {
                    iTxt += desp;  // Cifrar
                    if (iTxt >= longitud) iTxt -= longitud;
                } else {
                    iTxt -= desp;  // Descifrar
                    if (iTxt < 0) iTxt += longitud;
                }

                resultado += abc[iTxt];
                indiceClave = (indiceClave + 1) % clave.length;
            } else {
                resultado += c;  // Caracteres fuera del alfabeto se copian tal cual
            }
        }

        return resultado;
    };

    return {
        encode: function (txt, clave) {
            return proceso(txt, clave, true);
        },
        decode: function (txt, clave) {
            return proceso(txt, clave, false);
        }
    };
})();

function codificar() {
    const texto = document.getElementById("txt").value;
    const clave = document.getElementById("txtclave").value;
    if (texto && clave) {
        document.getElementById("respuesta").value = vigenere.encode(texto, clave);
    } else {
        alert("Por favor, completa ambos campos.");
    }
}

function decodificar() {
    const texto = document.getElementById("txt").value;
    const clave = document.getElementById("txtclave").value;
    if (texto && clave) {
        document.getElementById("respuesta").value = vigenere.decode(texto, clave);
    } else {
        alert("Por favor, completa ambos campos.");
    }
}

function reiniciar() {
    document.getElementById("txt").value = "";
    document.getElementById("txtclave").value = "";
    document.getElementById("respuesta").value = "";
}

function copiarTexto() {
    const resultado = document.getElementById("respuesta");
    if (resultado.value) {
        navigator.clipboard.writeText(resultado.value)
            .then(() => alert("Texto copiado al portapapeles."))
            .catch(() => alert("Error al copiar el texto."));
    } else {
        alert("No hay texto para copiar.");
    }
}
