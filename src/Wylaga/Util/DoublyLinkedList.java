package Wylaga.Util;

import java.util.ArrayList;

public class DoublyLinkedList<T>
{
    Node<T> root;

    private static class Node<T>
    {
        Node(T data)
        {
            this.data = data;
        }

        Node<T> prev;
        Node<T> next;
        T data;
    }

    private T get(int i)
    {
        Node<T> node = root;

        for(int index = 0; node != null && index < i; index++)
        {
            node = node.next;
        }

        return (node == null) ? null : node.data;
    }

    private void add(T t)
    {
        add(new Node<T>(t));
    }

    private void add(Node<T> node)
    {
        if(root == null)
        {
            root = node;
        }

        else
        {
            Node<T> temp = root;

            while(temp.next != null)
            {
                temp = temp.next;
            }

            temp.next = node;
            node.prev = temp;
        }
    }

    private void remove(T t)
    {
        remove(new Node<T>(t));
    }

    private void remove(Node<T> node)
    {
        if(root == null)
        {
            return;
        }

        else if(root.equals(node))
        {
            root = root.next;
        }

        else
        {
            Node<T> prev = root;
            Node<T> cur = root.next;

            while(!cur.equals(node) && cur.next != null)
            {
                prev = cur;
                cur = cur.next;
            }

            if(cur.equals(node))
            {
                prev.next = cur.next;
                if(cur.next != null)
                {
                    cur.next.prev = prev;
                }
            }

        }
    }

    public int length()
    {
        int i = 0;
        Node<T> node = root;
        while(node != null)
        {
            i++;
            node = node.next;
        }
        return i;
    }

    public T[] toArray()
    {
        return (T[]) toArrayList().toArray();
    }

    public ArrayList<T> toArrayList()
    {
        ArrayList<T> arrayList = new ArrayList<T>();
        Node<T> node = root;
        while(node != null)
        {
            arrayList.add(node.data);
            node = node.next;
        }
        return arrayList;
    }

}
