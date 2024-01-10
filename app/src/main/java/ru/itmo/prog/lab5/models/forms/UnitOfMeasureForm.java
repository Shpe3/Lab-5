package ru.itmo.prog.lab5.models.forms;

import ru.itmo.prog.lab5.exceptions.IncorrectInputInScriptException;
import ru.itmo.prog.lab5.models.UnitOfMeasure;
import ru.itmo.prog.lab5.utility.Interrogator;
import ru.itmo.prog.lab5.utility.console.Console;

import java.util.NoSuchElementException;

public class UnitOfMeasureForm extends Form<UnitOfMeasure> {
  private final Console console;

  public UnitOfMeasureForm(Console console) {
    this.console = console;
  }

  @Override
  public UnitOfMeasure build() throws IncorrectInputInScriptException {
    var fileMode = Interrogator.fileMode();

    String strUnitOfMeasure;
    UnitOfMeasure unitOfMeasure;
    while (true) {
      try {
        console.println("Список мер весов - " + UnitOfMeasure.names());
        console.println("Введите меру весов (или null):");
        console.ps2();

        strUnitOfMeasure = Interrogator.getUserScanner().nextLine().trim();
        if (fileMode)
          console.println(strUnitOfMeasure);

        if (strUnitOfMeasure.equals("") || strUnitOfMeasure.equals("null"))
          return null;
        unitOfMeasure = UnitOfMeasure.valueOf(strUnitOfMeasure.toUpperCase());
        break;
      } catch (NoSuchElementException exception) {
        console.printError("Мера весов не распознано!");
        if (fileMode)
          throw new IncorrectInputInScriptException();
      } catch (IllegalArgumentException exception) {
        console.printError("Меры весов нет в списке!");
        if (fileMode)
          throw new IncorrectInputInScriptException();
      } catch (IllegalStateException exception) {
        console.printError("Непредвиденная ошибка!");
        System.exit(0);
      }
    }
    return unitOfMeasure;
  }
}
