import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Time: O(n), n is the height of the tree
//Space: O(n), n will be the max amount of elements possible on a level of the tree

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        
        //null case check
        if(wordList.size() == 0) return 0;
        
        //creating a queue which we will use for BFS, starting with the beginWord 
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        
        //add all the words from the list to a hashset so we can do O(1) searches
        HashSet<String> allWords = new HashSet<>(wordList);
        //if there is no endWord in the list then no point in moving forward
        if(!allWords.contains(endWord)) return 0;
        
        //creating a level count which we will increment as we move froward through each level of the tree
        //initializing with 1 for the first level with the beginWord
        int level = 1;
        
        //looping through each level of the tree
        while(!q.isEmpty()) {
            int size = q.size();
            
            //looping through all the elements on each level
            for(int i=0; i < size; i++) {
                //poppin the element from queue and storing it in a string
                String word = q.poll();
                //converting the string to an array of characters
                char[] wordArray = word.toCharArray();
                
                //looping through each character of a word, one at a time
                for(int j=0; j < word.length(); j++) {
                    //storing each letter of the chracter in the word, one at a time as we move forward
                    char ch = wordArray[j];
                    //replacing the jth character with each cheacters in the alphabet to check all the different combinations
                    for(char c = 'a'; c <= 'z'; c++) {
                        //skipping if we end up making the character same as the one we are checking
                        if(wordArray[j] == c) continue;
                        wordArray[j] = c;
                        //creating the string from the newly formed word
                        String newWord = new String(wordArray);
                        //if the word equals to endWord then we increment the level and return the loop
                        if(newWord.equals(endWord)) return level + 1;
                        //if not then we check if newWord is one of the words given to us in the list
                        if(allWords.contains(newWord)) {
                            //if yes then we add it to the queue
                            q.add(newWord);
                            //and remove the word from the hashset as it is already visited
                            allWords.remove(newWord);
                        }
                    }
                    //re-initializing the character to the original characted so we can retain the word we're checking
                    wordArray[j] = ch;
                } 
            }
            level++;
        }
         return 0;
    }
}