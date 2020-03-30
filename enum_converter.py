path = input("Codefile : ")
file = open(path)
text = file.read().replace("\t","").replace("\n","")
file.close()

var = ""
imports = ""
reg = ""
for line in text.split(";"):
    print(line)
    try:
        done = False
        if not line[0] in ["/"]:
            caps_name = ""
            code = ""
            for char in line:
                if done:
                    code += char
                else:
                    if char == "(":
                        done = True
                    else:
                        caps_name += char
            split_name = caps_name.split("_")
            print(split_name)
            class_name = "Item"
            for x in range(0,len(split_name)):
                class_name += split_name[x][0].capitalize() + split_name[x][1:].lower()

            var+="\t\tpublic static final CustomItem "+caps_name+" = new "+class_name+"();\n"
            imports+="import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items."+class_name+";\n"
            reg+="register("+caps_name+");\n"
            
            file = open(class_name+".java","w")
            file.write("""package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class %s extends CustomItem {

	public %s() {
		super("%s", %s;
	}

}
"""%(class_name,class_name,caps_name,code))
            file.close()
    except IndexError:
        pass

print("Vars: ==============================")
print(var)
print("Imports: ===========================")
print(imports)
print("Registering: =======================")
print(reg)


