package DataStructures.RopeAndTrie.Exercise;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TextEditorImpl implements TextEditor {
    private Set<String> loggedUsers;
    private Trie<Deque<StringBuilder>> trie;

    public TextEditorImpl() {
        this.loggedUsers = new LinkedHashSet<>();
        this.trie = new Trie<>();
    }

    @Override
    public void login(String username) {
        this.loggedUsers.remove(username);
        this.loggedUsers.add(username);
        this.trie.insert(username, new ArrayDeque<>());
    }

    @Override
    public void logout(String username) {
        this.trie.insert(username, new ArrayDeque<>());
        this.loggedUsers.remove(username);
    }

    @Override
    public void prepend(String username, String string) {
        if (this.loggedUsers.contains(username)) {
            Deque<StringBuilder> stack = this.trie.getValue(username);
            StringBuilder sb = stack.isEmpty() ? new StringBuilder() : stack.peek();
            StringBuilder newSb = new StringBuilder(sb.toString());
            newSb.insert(0, string);
            stack.push(newSb);
        }
    }

    @Override
    public void insert(String username, int index, String string) {
        if (this.loggedUsers.contains(username)) {
            Deque<StringBuilder> stack = this.trie.getValue(username);
            StringBuilder sb = stack.isEmpty() ? new StringBuilder() : stack.peek();
            StringBuilder newSb = new StringBuilder(sb.toString());
            newSb.insert(index, string);
            stack.push(newSb);
        }
    }

    @Override
    public void substring(String username, int startIndex, int length) {
        if (this.loggedUsers.contains(username)) {
            Deque<StringBuilder> stack = this.trie.getValue(username);
            StringBuilder sb = stack.isEmpty() ? new StringBuilder() : stack.peek();

            if (sb.length() > 0) {
                StringBuilder newSb = new StringBuilder(sb.substring(startIndex, startIndex + length));
                stack.push(newSb);
            }
        }
    }

    @Override
    public void delete(String username, int startIndex, int length) {
        if (this.loggedUsers.contains(username)) {
            Deque<StringBuilder> stack = this.trie.getValue(username);
            StringBuilder sb = stack.isEmpty() ? new StringBuilder() : stack.peek();
            StringBuilder newSb = new StringBuilder(sb.toString());
            newSb.delete(startIndex, startIndex + length);
            stack.push(newSb);
        }
    }

    @Override
    public void clear(String username) {
        if (this.loggedUsers.contains(username)) {
            Deque<StringBuilder> stack = this.trie.getValue(username);
            StringBuilder newSb = new StringBuilder();
            stack.push(newSb);
        }
    }

    @Override
    public int length(String username) {
        if (this.loggedUsers.contains(username)) {
            Deque<StringBuilder> stack = this.trie.getValue(username);
            StringBuilder sb = stack.isEmpty() ? new StringBuilder() : stack.peek();

            return sb.length();
        }

        return 0;
    }

    @Override
    public String print(String username) {
        if (this.loggedUsers.contains(username)) {
            Deque<StringBuilder> stack = this.trie.getValue(username);
            StringBuilder sb = stack.isEmpty() ? new StringBuilder() : stack.peek();

            return sb.toString();
        }

        return "";
    }

    @Override
    public void undo(String username) {
        if (this.loggedUsers.contains(username)) {
            Deque<StringBuilder> stack = this.trie.getValue(username);
            if (!stack.isEmpty()) {
                stack.pop();
            }
        }
    }

    @Override
    public Iterable<String> users(String prefix) {
        if ("".equals(prefix)) {
            return this.loggedUsers;
        }

        return this.loggedUsers.stream()
                .filter(user -> user.startsWith(prefix))
                .collect(Collectors.toList());
    }
}
