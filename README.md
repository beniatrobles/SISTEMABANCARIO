# 💳 Sistema Bancario en Java (Swing + MySQL)

Este proyecto es una aplicación de escritorio desarrollada en **Java** con **Swing** y conectada a una base de datos **MySQL**, que simula las operaciones básicas de un sistema bancario.

---

## ✨ Funcionalidades principales

- **Gestión de clientes**  
  - Registro y listado de clientes con datos personales.  
  - Asociación de cuentas bancarias y usuarios.  

- **Gestión de cuentas bancarias**  
  - Crear nuevas cuentas.  
  - Eliminar cuentas (respetando restricciones de integridad).  
  - Actualización de saldos.  

- **Operaciones de cajero**  
  - **Ingresar dinero** en una cuenta.  
  - **Retirar dinero** de una cuenta.  

- **Transferencias**  
  - Transferencias entre cuentas propias o hacia cuentas externas.  
  - Registro automático en la tabla de **transacciones** (`tb_transaccion`).  

- **Historial de transacciones**  
  - Visualización de todas las transferencias realizadas en una tabla.  

---

## 🗄️ Estructura de la base de datos

- **tb_cliente** → Información de clientes.  
- **tb_cuenta** → Cuentas bancarias asociadas a los clientes.  
- **tb_usuario** → Usuarios para iniciar sesión.  
- **tb_transaccion** → Registro de depósitos, retiros y transferencias.  

Todas las tablas están relacionadas mediante **claves foráneas** para garantizar la integridad referencial.

---

## 🛠️ Tecnologías utilizadas

- **Lenguaje**: Java (JDK 17+)  
- **Interfaz gráfica**: Swing (JInternalFrame, JTable, JComboBox, etc.)  
- **Base de datos**: MySQL  
- **Conexión**: JDBC  
- **Patrón**: Separación en capas (`vista`, `modelo`, `controlador`, `conexion`)  

---

## ▶️ Ejecución

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tuusuario/sistema-bancario-java.git
