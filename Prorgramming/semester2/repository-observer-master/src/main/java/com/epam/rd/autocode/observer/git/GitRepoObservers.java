package com.epam.rd.autocode.observer.git;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

    public class GitRepoObservers {

    public static Repository newRepository() {
        return new repo1();
    }
        public static WebHook mergeToBranchWebHook(String branchName) {
            return new repo2(branchName);
        }

        public static WebHook commitToBranchWebHook(String branchName) {
            return new repo3(branchName);
        }
    }
        class repo1 implements Repository {
            ArrayList<WebHook> qebHook = new ArrayList<>();
            ArrayList<String> branchy = new ArrayList<>();
            repo1(){
                branchy.add("main");}
            @Override
            public Branch getBranch(String name) {
                if(branchy.contains(name)){
                    return new Branch(name);
            }
            else {
                    return null;
                }

        }

            @Override
            public Branch newBranch(Branch sourceBranch, String name) {
                if(getBranch(name) == null){
                    if(!(sourceBranch == null) && (!(getBranch(sourceBranch.toString()) == null))){
                        Branch newBranch = new Branch(name);
                        branchy.add(String.valueOf(newBranch));
                        newBranch.branchCommit.addAll(sourceBranch.branchCommit);
                        return newBranch;
                    }
                    else {
                        throw new IllegalArgumentException();
                    }
                }
                else
                {
                    throw new IllegalArgumentException();
                }
            }


            @Override
            public Commit commit(Branch branch, String author, String[] changes) {
                Commit commit = new Commit(author, changes);
                branch.branchCommit.add(commit);

                ArrayList<Commit> newCommit = new ArrayList<>();
                newCommit.add(commit);
                for(WebHook webHook : qebHook){
                    if((webHook.branch()).equals(branch.toString())){
                        webHook.onEvent(new Event(Event.Type.COMMIT, branch, newCommit));
                 break;
                    }
                }
                return commit;
            }
                //use get
            @Override
            public void merge(Branch sourceBranch, Branch targetBranch) {
                ArrayList<Commit> newOne = new ArrayList<>();
                for(int i = 0; i < sourceBranch.branchCommit.size(); i++){
                    if(!targetBranch.branchCommit.contains(sourceBranch.branchCommit.get(i))){
                        targetBranch.branchCommit.add(sourceBranch.branchCommit.get(i));
                        newOne.add(sourceBranch.branchCommit.get(i));
                    }
                }
                int index = 0;
                for(int i = 0; i < qebHook.size(); i ++){
                    if((qebHook.get(i).branch()).equals(targetBranch.toString()) && (qebHook.get(i).type() == Event.Type.MERGE)){
                        index=i;
                        if (newOne.size() != 0){
                            qebHook.get(index).onEvent(new Event(Event.Type.MERGE, targetBranch, newOne));
                        }
                        break;
                    }
                }
            }

            @Override
            public void addWebHook(WebHook webHook) {
            qebHook.add(webHook);
            }
    }
    class repo2 implements WebHook {
        String nameing;
        ArrayList<Event> merges = new ArrayList<>();

        public repo2(String nameing){
            this.nameing = nameing;
        }

        @Override
        public String branch() {
            return this.nameing;
        }

        @Override
        public Event.Type type() {
            return Event.Type.MERGE;
        }

        @Override
        public List<Event> caughtEvents() {
            return merges;
        }

        @Override
        public void onEvent(Event event) {
            if (event.type() == Event.Type.MERGE) {
                merges.add(event);
            }
        }
    }
    class repo3 implements WebHook{
        String nameing;
        ArrayList<Event> commits = new ArrayList<>();
        int type;
        public repo3(String nameing){
            this.nameing = nameing;
            this.type = 0;
        }
        @Override
        public String branch(){
            return this.nameing;
        }
        @Override
        public Event.Type type(){
            return Event.Type.COMMIT;
        }
        @Override
        public ArrayList<Event> caughtEvents(){
            return commits;
        }
        @Override
        public void onEvent(Event event){
            if (event.type() == Event.Type.COMMIT){
                commits.add(event);
            }
        }
    }

