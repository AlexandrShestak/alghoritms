package com.shestakam.hadoopStack;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

import java.io.IOException;
import java.util.Iterator;

public class Hdfs {

  public static class MyMapper implements Mapper<Object, Text, Text, IntWritable>{

    @Override
    public void map(Object key, Text value, OutputCollector output, Reporter reporter) throws IOException {
      String line = value.toString();
      String[] lineSplit = line.split("\\t");
      output.collect(new Text(lineSplit[1]), new IntWritable(Integer.parseInt(lineSplit[2])));
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public void configure(JobConf job) {

    }
  }

  public static class MyReducer implements Reducer<Text, IntWritable, Text, IntWritable> {

    @Override
    public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
      int resultForKey = 0;
      while (values.hasNext()) {
        resultForKey += values.next().get();
      }

      output.collect(key, new IntWritable(resultForKey));
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public void configure(JobConf job) {

    }

  }


  public static void main(String[] args) throws Exception {
    JobConf conf = new JobConf(Task1.class);
    conf.setJobName("Task1");


    FileInputFormat.setInputPaths(conf, new Path(args[0]));
    FileOutputFormat.setOutputPath(conf, new Path(args[1]));

    conf.setMapperClass(MyMapper.class);
    conf.setCombinerClass(MyReducer.class);
    conf.setReducerClass(MyReducer.class);

    conf.setOutputKeyClass(Text.class);
    conf.setOutputValueClass(IntWritable.class);
    JobClient.runJob(conf);
  }
}
