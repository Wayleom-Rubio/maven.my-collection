package com.github.curriculeon;

import java.util.Iterator;

public class MyArrayList<SomeType extends Object> implements MyCollectionInterface<SomeType>{

    private int index;
    private SomeType[] content;
    private static final Integer RE_SIZE = 5;

    public MyArrayList() {
        index = 0;
        this.content = (SomeType[]) new Object[10];
    }

    public MyArrayList(SomeType[] valuesToBePopulatedWith) {
        content = (SomeType[]) new Object[valuesToBePopulatedWith.length];
        System.arraycopy(valuesToBePopulatedWith,0,content,0,valuesToBePopulatedWith.length);
        index = valuesToBePopulatedWith.length;
    }

    @Override
    public void add(SomeType objectToAdd) {
        Integer threshold = content.length-1;
        // Do we have enough space
        if(index >= threshold) {
            // If we don't enough space , we need create more space
            Integer newSize = content.length + RE_SIZE;
            SomeType[] newContent = (SomeType[]) new Object[newSize];
            System.arraycopy(content, 0, newContent, 0 , content.length);
            content = newContent;
        }
        // place element where ever index is
        content[index] = objectToAdd;
        // increment index by 1
        index++;
    }

    @Override
    public void remove(SomeType objectToRemove) {

        SomeType[] array = (SomeType[]) new Object[content.length];
        Integer newIndex = 0;
        for (SomeType currentElement : content) {
            if (currentElement == null) continue;
            if (!currentElement.equals(objectToRemove))
            {
                array[newIndex] = currentElement;
                newIndex++;
            }
            else index--;
        }
        content = array;
    }

    @Override
    public void remove(int indexOfObjectToRemove) {

        SomeType[] array = (SomeType[]) new Object[content.length-1];
        int track = 0;
        for(int i = 0; i < content.length; i++)
            if (i != indexOfObjectToRemove) {
                array[track] = content[i];
                track++;
            }
        content = array;
        index--;
    }

    @Override
    public SomeType get(int indexOfElement) {
        if(indexOfElement > -1 && indexOfElement < index)
            return content[indexOfElement];
        return null;
    }

    @Override
    public Boolean contains(SomeType objectToCheckFor) {
        for(SomeType type : content){
            if(type == objectToCheckFor) return true;
        }
        return false;
    }

    @Override
    public Integer size() {
        return index;
    }

    @Override
    public Iterator iterator() {
        return new MyArrayListIterator<>(this);
    }

    public static class MyArrayListIterator<SomeType> implements Iterator<SomeType> {
        private MyArrayList<SomeType> list;
        private int currentIndex;

        public MyArrayListIterator(MyArrayList<SomeType> list) {
            this.list = list;
            this.currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < list.size();
        }

        @Override
        public SomeType next() {
            return list.get(currentIndex++);
        }
    }
}
