package de.sakurajin.evenbetterarcheology.api.DatagenEngine;

import net.minecraft.util.Identifier;
import net.minecraft.util.InvalidIdentifierException;

public class TagIdentifier extends Identifier {

    public TagIdentifier(String namespace, String path) {
        super(tagValidateNamespace(namespace, path), tagValidatePath(namespace,path), null);
    }

    protected TagIdentifier(String[] namespaceAndPath) {
        this(namespaceAndPath[0], namespaceAndPath[1]);
    }

    public TagIdentifier(String name) {
        this(tagSplit(name, ':'));
    }

    public static TagIdentifier ofDefault(String path, String defaultNamespace) {
        if(path.contains(":")){
            return new TagIdentifier(path);
        }else{
            return new TagIdentifier(defaultNamespace, path);
        }
    }

    protected static String tagValidatePath(String namespace, String path) {
        if(!path.matches("^[a-z0-9/._-]+$")) {
            throw new InvalidIdentifierException("Non [a-z0-9/._-] character in path of location: " + namespace + ":" + path);
        }

        return path;
    }

    protected static String tagValidateNamespace(String namespace, String path) {
        if(!namespace.matches("^#?[a-z0-9_.-]+$")) {
            throw new InvalidIdentifierException("Non [a-z0-9#_.-] character in namespace of location: " + namespace + ":" + path);
        }
        return namespace;
    }

    protected static String[] tagSplit(String name, char separator) {
        if(name.startsWith("#")) {
            var split = split(name.substring(1), separator);
            split[0] = "#" + split[0];
            return split;
        }else{
            return split(name, separator);
        }
    }

}
