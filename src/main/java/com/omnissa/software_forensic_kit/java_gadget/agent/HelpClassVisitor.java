/***************************************************
 * Copyright 2020 Omnissa, LLC.
 * SPDX-License-Identifier: BSD-2-Clause
 ***************************************************/
package com.omnissa.software_forensic_kit.java_gadget.agent;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class HelpClassVisitor extends ClassVisitor {
    private String className;
    private String methodName;
           
    public HelpClassVisitor(ClassVisitor cv, String pClassName, String methodName) {
    	super(Opcodes.ASM5, cv);
		this.className = pClassName;
		this.methodName = methodName;
		
		
    }
    
    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
    	MethodVisitor mv = super.visitMethod(access, name, desc, signature,
                exceptions);
    	
   
    	if(true) {//name.equals(this.methodName)) {
    		Parameter paramM[] = null;
    	
    		System.out.println(String.format("Software_Forensic  method %s - %s", name, desc));
    	
	    	 try {
	    		 String newClassName = className.replace("/", ".");
	    		    Class<?> act = Class.forName(newClassName);
	    		    System.out.println(newClassName);
	    		    System.out.println(act.getName());
	    		    System.out.println(act.getDeclaredMethods());
	    		    Method fld[] = act.getDeclaredMethods();
	    	         for (int i = 0; i < fld.length; i++)
	    	         {
	    	        	 if(fld[i].getName().equals(this.methodName)) {
		    	             System.out.println("Method Name is : " + fld[i].getName());
		    	             paramM = fld[i].getParameters();
		    	       
		    	             for (int g = 0; g<paramM.length; g++) {
		    	            	 System.out.println("Variable Name is : " + paramM[g].toString());
		    	             }
	    	        	 }
	    	         }   
	    		 } catch (ClassNotFoundException e) {
	    		        e.printStackTrace();
	    		}
    	
	    	 return new HelpMethodVisitor(access, mv, name, className, desc, paramM);
    	}
    	else
    	return mv;
    
    } 
    
   
   
}
