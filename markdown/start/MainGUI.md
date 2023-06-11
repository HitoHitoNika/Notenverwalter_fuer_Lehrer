# MainGUI Klasse

Die MainGUI Klasse dient lediglich zum Starten des Programms.
Ziel war es die Main Methode aus den GUIs rauszuhalten und diese übersichtlicher zuhalten.

## Methoden

<details>
<summary> 1.invokeLater </summary>

- Dient dazu der run() einen eigenen Thread zu geben und diese Aufzurufen

</details>
<details>
<summary> 2. run </summary>

- Erstell ein Objekt des ersten GUIs und lässt dieses erscheinen
- Im Falle einer Exception wird diese abgefangen und eine Ausgabe mit entsprechenden Verweis getätigt

</details>